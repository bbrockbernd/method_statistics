![Java CI with Gradle](https://github.com/bbrockbernd/method_statistics/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![codecov](https://codecov.io/gh/bbrockbernd/method_statistics/branch/master/graph/badge.svg)](https://codecov.io/gh/bbrockbernd/method_statistics)

Method and Markdown Statistics Plugin
=====================================

As the name suggests, this is an IntelliJ plugin for analyzing your methods and markdown files.

How to build:
-------------
You can simply download or clone the project and run `gradle runIde`.

Features:
---------
Both implemented features can be found in the tool menu bar:

<img src="images/actions.png" alt="Tools" width="200"/>

### Method summary:

For the method summary we measure Cyclomatic Complexity(CC) and Lines of Code(LOC) and show their distribution over a class:

<img src="images/methods.png" alt="Example distribution of CC and LOC" width="700"/>

<img src="images/method_signatures.png" alt="Method signature" width="700"/>

### Markdown link summary:

For our markdown feature we display basic features like number of links and paragraphs as seen in the example:

<img src="images/md_files.png" alt="Markdown file stats" width="700"/>


Clicking on one of the files gives a summary of the links, including if the linked file is inside the repo:

<img src="images/md_links.png" alt="Markdown link stats" width="700"/>

Note that double clicking of one of those links repositions your cursor on the respective link.