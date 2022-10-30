docker-down:
	docker-compose --project-directory=$(shell pwd)/docker down

docker-up: docker-down
	docker-compose --project-directory=$(shell pwd)/docker up -d
	docker-compose --project-directory=$(shell pwd)/docker ps
	sleep 10
	docker exec -ti mongodb-cds-test-1 sh -c '/opt/mongo/init-rs.js'