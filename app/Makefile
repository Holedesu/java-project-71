.DEFAULT_GOAL := build-run
run-dist:
	./build/install/app/bin/app
install-dist:
	./gradlew installDist
clean:
	./gradlew clean
lint:
	./gradlew checkstyleMain
test:
	./gradlew checkstyleTest
build-run:
	./gradlew build run
build:
	./gradlew build
report:
	./gradlew jacocoTestReport

.PHONY: build