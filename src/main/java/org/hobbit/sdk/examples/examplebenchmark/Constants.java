package org.hobbit.sdk.examples.examplebenchmark;


/**
 * @author Pavel Smirnov
 */

public class Constants {

    protected static final String uri = "http://w3id.org/dice-research/list-gpus-system/ontology#";

    public static String GIT_USERNAME = "gitadmin";
    public static String GIT_REPO_PATH = "git.project-hobbit.eu:4567/"+GIT_USERNAME+"/";
    //public static String GIT_REPO_PATH = "";

    public static String PROJECT_NAME = "list-gpus-system";

    //use these constants within BenchmarkController
    public static final String BENCHMARK_IMAGE_NAME = GIT_REPO_PATH+PROJECT_NAME +"/benchmark-controller";
    public static final String DATAGEN_IMAGE_NAME = GIT_REPO_PATH+PROJECT_NAME +"/datagen";
    public static final String TASKGEN_IMAGE_NAME = GIT_REPO_PATH+PROJECT_NAME +"/taskgen";
    public static final String EVAL_STORAGE_IMAGE_NAME = GIT_REPO_PATH+PROJECT_NAME +"/eval-storage";
    public static final String EVALMODULE_IMAGE_NAME = GIT_REPO_PATH+PROJECT_NAME +"/eval-module";
    public static final String SYSTEM_IMAGE_NAME = GIT_REPO_PATH+PROJECT_NAME +"/system-adapter";

    public static final String BENCHMARK_URI = uri+PROJECT_NAME;
    public static final String SYSTEM_URI = uri+PROJECT_NAME+"/system";

    public static final String SDK_BUILD_DIR_PATH = ".";  //build directory, temp docker file will be created there
    public static final String SDK_WORK_DIR_PATH = "/usr/src/"+PROJECT_NAME;

    public static final byte INPUT_DATA_COMMAND = 101;
    public static final byte OUTPUT_DATA_COMMAND = 102;

}
