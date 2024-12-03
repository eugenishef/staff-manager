#!/bin/bash

docker stop staff || true
docker rm staff || true

docker run -d \
  --name staff \
  -e POSTGRES_DB=staff \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:11.22-bullseye

if [ $? -eq 0 ]; then
  echo "База данных 'staff' успешно создана и запущена."
else
  echo "Не удалось создать или запустить базу данных."
fi