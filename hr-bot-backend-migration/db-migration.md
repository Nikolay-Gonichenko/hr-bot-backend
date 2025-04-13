# Миграции
## Запуск миграций на локальной БД

```shell
mvn process-resources liquibase:update@migrate-hr-bot "-Pmigrate-hr-bot" "-Ddb.url=jdbc:postgresql://localhost:5433/hrbot?currentSchema=hrbot" "-Ddb.login=hrbot" "-Ddb.password=hrbot" "-DskipTests=true"
```