WRAPPER_IMAGE = git.project-hobbit.eu:4567/gitadmin/list-gpus-system/nvidia-smi-wrapper

install-deps:
	mvn validate

test-benchmark:
	mvn -Dtest=BenchmarkTest#checkHealth test

package:
	mvn -DskipTests -DincludeDeps=true package

build-images:
	docker build -t $(WRAPPER_IMAGE) nvidia-smi-wrapper
	mvn -Dtest=BenchmarkTest#buildImages surefire:test

test-dockerized-benchmark:
	mvn -Dtest=BenchmarkTest#checkHealthDockerized test


push-images:
	docker push $(WRAPPER_IMAGE)
	docker push git.project-hobbit.eu:4567/gitadmin/list-gpus-system/system-adapter
