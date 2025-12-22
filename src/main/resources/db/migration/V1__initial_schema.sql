CREATE SCHEMA IF NOT EXISTS app;

SET search_path TO app;

CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    password_hash TEXT NOT NULL,


    version BIGINT NOT NULL DEFAULT 0,
    created_at timestamptz NOT NULL DEFAULT now(),
    updated_at timestamptz,

    CONSTRAINT ck_users_username_lowercase
    CHECK (username = lower(username)),

    CONSTRAINT ck_users_username_trimmed
    CHECK (username = trim(username)),

    CONSTRAINT ck_users_username_not_blank
    CHECK (length(username) > 0),

    CONSTRAINT ck_users_email_lowercase
    CHECK (email = lower(email)),

    CONSTRAINT ck_users_email_trimmed
    CHECK (email = trim(email)),

    CONSTRAINT ck_users_email_not_blank
    CHECK (length(email) > 0),

    CONSTRAINT uq_users_username
    UNIQUE (username),

    CONSTRAINT uq_users_email
    UNIQUE (email)

    );


CREATE TABLE IF NOT EXISTS categories (
    category_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(254) NOT NULL,
    description TEXT,
    is_default BOOLEAN DEFAULT FALSE NOT NULL,

    version BIGINT NOT NULL DEFAULT 0,
    created_at timestamptz NOT NULL DEFAULT now(),
    updated_at timestamptz,

    user_id BIGINT NOT NULL,

    CONSTRAINT ck_categories_name_lowercase
    CHECK (name = lower(name)),

    CONSTRAINT ck_categories_name_trimmed
    CHECK (name = trim(name)),

    CONSTRAINT ck_categories_name_not_blank
    CHECK (length(name) > 0),

    CONSTRAINT ck_categories_description_not_blank
    CHECK (description is NULL OR length(trim(description)) > 0),

    CONSTRAINT uq_categories_user_category_id
    UNIQUE (user_id, category_id),

    CONSTRAINT  uq_categories_user_category_name
    UNIQUE (user_id, name),


    CONSTRAINT fk_categories_user
    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON DELETE CASCADE

    );

CREATE UNIQUE INDEX IF NOT EXISTS ux_categories_default_user_id
    ON categories (user_id)
    WHERE is_default = True;

CREATE INDEX IF NOT EXISTS ix_categories_user_id
    ON categories (user_id);

--Function and trigger for preventing deletions
CREATE OR REPLACE FUNCTION protect_default_categorie()
    RETURNS trigger AS $$
BEGIN
    IF (OLD.is_default = TRUE) THEN
        RAISE EXCEPTION 'Cannot delete default category.'
            USING ERRCODE = '23000';
    END IF;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;


DROP TRIGGER IF EXISTS trg_protect_default_category ON categories;

CREATE TRIGGER trg_protect_default_category
    BEFORE DELETE ON categories
    FOR EACH ROW
EXECUTE FUNCTION protect_default_categorie();


CREATE TABLE IF NOT EXISTS expenses (
    expense_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(254) NOT NULL,
    amount BIGINT NOT NULL,
    description TEXT,

    version BIGINT NOT NULL DEFAULT 0,
    created_at timestamptz NOT NULL DEFAULT now(),
    updated_at timestamptz,

    category_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT ck_expenses_name_not_blank
    CHECK (length(trim(name)) > 0),

    CONSTRAINT ck_expenses_description_not_blank
    CHECK (length(trim(description)) > 0),

    CONSTRAINT ck_expenses_amount_not_negative
    CHECK (amount >= 0),

    CONSTRAINT fk_expenses_user_id
    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON DELETE CASCADE,

    CONSTRAINT fk_expenses_category_ownership
    FOREIGN KEY (category_id, user_id)
    REFERENCES categories(category_id, user_id)
    ON DELETE CASCADE
    );

CREATE INDEX IF NOT EXISTS ix_expenses_user_id
    ON expenses(user_id);
CREATE INDEX IF NOT EXISTS ix_expenses_category_id
    ON expenses(category_id);

--Function and trigger for updating all tables with the time of updation
CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_users_updated_at BEFORE UPDATE ON users
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER trg_categories_updated_at BEFORE UPDATE ON categories
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER trg_expenses_updated_at BEFORE UPDATE ON expenses
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();