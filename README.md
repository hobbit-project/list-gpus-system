# List GPUs System
A simple system to check which GPUs are available in systems benchmarked with HOBBIT.

It uses "String Function Benchmark".

You can run it without GUI by using the provided [`benchmark-parameters.ttl`](./benchmark-parameters.ttl):

```sh
docker run \
-e "BENCHMARK=http://w3id.org/dice-research/string-function-benchmark/ontology#benchmark" \
-e "SYSTEM=http://w3id.org/dice-research/list-gpus-system/ontology#system" \
-e "BENCHMARK_PARAM_FILE=/benchmark-parameters.ttl" \
-v "$(pwd)/benchmark-parameters.ttl:/benchmark-parameters.ttl" \
-e "HOBBIT_RABBIT_HOST=rabbit" \
--net hobbit-core \
-v "/var/run/docker.sock:/var/run/docker.sock" \
-e "HOBBIT_REDIS_HOST=redis" \
hobbitproject/hobbit-platform-controller \
java -cp target/platform-controller.jar org.hobbit.core.run.ComponentStarter org.hobbit.controller.test.StartBenchmarkRequest
```

New experiment ID will be given; use it in the following SPARQL query to see the results:

```sparql
select distinct ?s ?p ?o where {?s ?p ?o FILTER(?s = <http://w3id.org/hobbit/experiments#EXPERIMENT_ID>)}
```

## See also

How to configure GPUs to be available in HOBBIT: https://hobbit-project.github.io/gpu.html
