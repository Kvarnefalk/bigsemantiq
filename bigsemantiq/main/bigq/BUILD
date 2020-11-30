load("@io_bazel_rules_scala//scala_proto:scala_proto.bzl", "scala_proto_library")

package(
    default_visibility = ["//visibility:public"],
)

scala_proto_library(
    name = "bigsemantiq_proto",
    deps=[
        "@com_google_zetasql//zetasql/public:type_proto",
        "@com_google_zetasql//zetasql/public:builtin_function_java_proto",
        "@com_google_zetasql//zetasql/public:deprecation_warning_java_proto",
        "@com_google_zetasql//zetasql/public:error_location_java_proto",
        "@com_google_zetasql//zetasql/public:function_java_proto",
        "@com_google_zetasql//zetasql/public:options_java_proto",
        "@com_google_zetasql//zetasql/public:parse_resume_location_java_proto",
        "@com_google_zetasql//zetasql/public:simple_table_java_proto",
        "@com_google_zetasql//zetasql/public:type_proto_java_proto",
        "@com_google_zetasql//zetasql/public:value_java_proto",
        "@com_google_zetasql//zetasql/resolved_ast:resolved_node_kind_java_proto",
    ]
)

scala_binary(
    name = "bigsemantiq",
    srcs = glob(["*.scala"]),
    main_class = "bigsemantiq.BigSemantiq",
    deps = [
        "@com_google_zetasql//java/com/google/zetasql:analyzer",
        "@com_google_zetasql//java/com/google/zetasql:channel_provider",
        "@com_google_zetasql//java/com/google/zetasql:client",
        "@com_google_zetasql//java/com/google/zetasql:jni_channel",
        "@com_google_zetasql//java/com/google/zetasql:types",
        "@com_google_zetasql//java/com/google/zetasql/resolvedast",
        ":bigsemantiq_proto"
    ]    # https://cs.opensource.google/tensorflow/tensorflow/+/master:tensorflow/lite/java/jni/BUILD;l=8?q=jni%20file:BUILD
    # Look at how they generate jni.h
)

