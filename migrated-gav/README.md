This is an example of a pom using a plugin that has migrated from one group to another

Intellij shows the usage of the old location of the plugin as an error, but maven interprets it as expected and is able
to execute the plugin even if the old gav is used.

Issue submitted at https://youtrack.jetbrains.com/issue/IDEA-286052