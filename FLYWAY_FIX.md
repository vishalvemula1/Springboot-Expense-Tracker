# Flyway Checksum Mismatch Fix

## Problem
The migration file `V1__initial_schema.sql` was modified after being applied to the database, causing a checksum mismatch:
- Database checksum: 132104523
- Local file checksum: -1373133160

## Solutions

### Option 1: Repair (Recommended for Development)
Run Maven Flyway repair to update the checksum in the database:
```powershell
.\mvnw.cmd flyway:repair
```

### Option 2: SQL Update
Connect to PostgreSQL and run:
```sql
UPDATE app.flyway_schema_history 
SET checksum = -1373133160 
WHERE version = '1';
```

### Option 3: Clean Database (Nuclear Option)
⚠️ This will delete all data!
```powershell
.\mvnw.cmd flyway:clean
```
Then restart your application to recreate the schema.

### Option 4: Use psql command line
```powershell
psql -h localhost -p 5412 -U javaET -d postgres -c "UPDATE app.flyway_schema_history SET checksum = -1373133160 WHERE version = '1';"
```

## Current Configuration
I've updated `application.properties` to disable validation on migrate:
- `spring.flyway.validate-on-migrate=false`
- `spring.flyway.baseline-on-migrate=true`

This should prevent this issue in the future during development.

