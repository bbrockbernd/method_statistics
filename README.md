![Java CI with Gradle](https://github.com/bbrockbernd/method_statistics/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![codecov](https://codecov.io/gh/bbrockbernd/method_statistics/branch/master/graph/badge.svg)](https://codecov.io/gh/bbrockbernd/method_statistics)

Method and Markdown Statistics Plugin
=====================================

As the name suggests, this is an IntelliJ plugin for analyzing your methods and markdown files.

How to build
-------------
You can simply download or clone the project and run `gradle runIde` using IntelliJ IDEA. A new IDE will open up where you can follow the steps below.

Features
---------
Both implemented features can be found in the Tools menu bar:

<img src="images/actions.png" alt="Tools" width="200"/>

### Method statistics plugin

For the method summary we measure the Cyclomatic Complexity(CC) and the Lines of Code(LOC) metrics and show their distribution over a class. Clicking on a method in the table above will show its summary.

<img src="images/methods.png" alt="Example distribution of CC and LOC" width="800"/>

<img src="images/method_signatures.png" alt="Method signature" width="800"/>

Double clicking on a method will reposition the mouse cursor on the respective method in the opened class.

### Markdown statistics plugin

For our markdown feature we display basic features like number of links and paragraphs as seen in the example:

<img src="images/md_files.png" alt="Markdown file stats" width="800"/>


Clicking on one of the files gives a summary of the links, including if the linked file is inside the repo:

<img src="images/md_links.png" alt="Markdown link stats" width="800"/>

Note that double clicking of one of those links repositions your cursor on the respective link.
