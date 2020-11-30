
##########################################################################################
#                                   External Deps                                       #
##########################################################################################

workspace(name = "com_github_jkvarnefalk_bigsemantiq")

load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

git_repository(
    name = "com_google_zetasql",
    tag = "2020.10.1",
    remote = "https://github.com/google/zetasql",
    patches = [
        # We have to patch the project to find the jni.h when imported. Inspired by how they do it in the tensorflow project.
        # https://cs.opensource.google/tensorflow/tensorflow/+/master:tensorflow/lite/java/jni/BUILD;l=8?q=jni%20file:BUILD
        "@com_github_jkvarnefalk_bigsemantiq//bazel:zetasql_jni.patch",
        # The strings class has no implementations for the methods. Here is a patch from this PR:https://github.com/google/zetasql/pull/31
        # that implements the necessary methods.
        "@com_github_jkvarnefalk_bigsemantiq//bazel:zetasql_strings.patch",
        ]
)

load("@com_google_zetasql//bazel:zetasql_deps_step_1.bzl", "zetasql_deps_step_1")

zetasql_deps_step_1()

load("@com_google_zetasql//bazel:zetasql_deps_step_2.bzl", "zetasql_deps_step_2")

zetasql_deps_step_2()

load("@com_google_zetasql//bazel:zetasql_deps_step_3.bzl", "zetasql_deps_step_3")

zetasql_deps_step_3()

load("@com_google_zetasql//bazel:zetasql_deps_step_4.bzl", "zetasql_deps_step_4")

zetasql_deps_step_4()
##########################################################################################
#                           SCALA BAZEL SETUP                                            #
##########################################################################################

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# bazel-skylib 0.8.0 released 2019.03.20 (https://github.com/bazelbuild/bazel-skylib/releases/tag/0.8.0)
skylib_version = "0.8.0"
http_archive(
    name = "bazel_skylib",
    type = "tar.gz",
    url = "https://github.com/bazelbuild/bazel-skylib/releases/download/{}/bazel-skylib.{}.tar.gz".format (skylib_version, skylib_version),
    sha256 = "2ef429f5d7ce7111263289644d233707dba35e39696377ebab8b0bc701f7818e",
)

rules_scala_version="8866f55712b30bbb96335cc11bc5ae5aad5cf8d4" # update this as needed

http_archive(
    name = "io_bazel_rules_scala",
    strip_prefix = "rules_scala-%s" % rules_scala_version,
    type = "zip",
    url = "https://github.com/bazelbuild/rules_scala/archive/%s.zip" % rules_scala_version,
    sha256 = "cdc13aba7f0f89ae52c9c50394a10f24ac0e18923108598ac9dafce5be6a789a",
)

# Stores Scala version and other configuration
# 2.12 is a default version, other versions can be use by passing them explicitly:
# scala_config(scala_version = "2.11.12")
load("@io_bazel_rules_scala//:scala_config.bzl", "scala_config")
scala_config()

load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains")
scala_register_toolchains()

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()

load("@io_bazel_rules_scala//testing:scalatest.bzl", "scalatest_repositories", "scalatest_toolchain")
scalatest_repositories()
scalatest_toolchain()

## TODO: Needed from this issue https://github.com/bazelbuild/rules_scala/issues/768
load("@io_bazel_rules_scala//scala_proto:toolchains.bzl", "scala_proto_register_toolchains")
scala_proto_register_toolchains()

load("@io_bazel_rules_scala//scala_proto:scala_proto.bzl", "scala_proto_repositories")
scala_proto_repositories()

