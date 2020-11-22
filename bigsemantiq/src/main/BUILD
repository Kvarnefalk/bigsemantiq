package(default_visibility = ["//visibility:public"])


java_binary(
    name = "foo",
    srcs = glob(["*.java"]),
    main_class="bigsemantiq.src.main.FooRunner",
    deps = [
        "@com_google_zetasql//java/com/google/zetasql:types",
        "@com_google_zetasql//java/com/google/zetasql:channel_provider",
        "@com_google_zetasql//java/com/google/zetasql:jni_channel",
        "@com_google_zetasql//java/com/google/zetasql:client",
        "@com_google_zetasql//java/com/google/zetasql:analyzer",
        "@com_google_zetasql//java/com/google/zetasql/resolvedast:resolvedast",
    ]

    # https://cs.opensource.google/tensorflow/tensorflow/+/master:tensorflow/lite/java/jni/BUILD;l=8?q=jni%20file:BUILD
    # Look at how they generate jni.h
)

