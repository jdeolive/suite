OpenGeo Suite README
====================

These instructions describe how to build platform independent components of OpenGeo Suite.

## Prerequisites

The following base software packages are required.

* [Java Development Kit (JDK)](http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html) - 1.6+
* [Apache Ant](http://ant.apache.org/bindownload.cgi) - 1.8+
* [Apache Ivy](http://ant.apache.org/ivy/download.cgi) - 2.3+
* [Git](http://git-scm.com/) - 1.7.10+*

Some modules require additional packages:

* [Apache Maven](http://maven.apache.org/download.html) - 3.0+
* [JSTools](https://github.com/whitmo/jstools) - Latest
* [Sphinx](http://sphinx.pocoo.org/) - 1.0+ (the full build requires LaTeX support)

## Quickstart

1. Clone the repository:

        % git clone git://github.com/opengeo/suite.git suite
        % cd suite

1. Initialize submodule dependencies:

        % git submodule update --init --recursive

1. Do a full build:

        % ant

1. Or build the module of your choice:

        % cd docs
        % ant 

## Build System Overview

The suite repository is made up a number of modules (ie projects). During development 
typically modules are built individually as opposed to all at once. The primary build 
tool for suite is Ant. For some modules the ant script delegates to the modules native build tool such as Maven or Sphinx. 

All top level modules have a `build.xml` that defines the following three targets:

1. `build` - Builds the project, the result of this is something deployable in the development environment. This target is the default.
1. `clean` - Cleans the project deleting all build artifacts. 
1. `assemble` - Assembles the project into a zip archive suitable for deployment.

Building for development purposes typically looks like:

    ant clean build

Building for deployment purposes typically looks like:

    ant clean build assemble

The [build](build) directory contains common build files used by modules.

 * [common.xml](build/common.xml) - Common ant targets used by module 
 build files. Every module build file imports this file as the first step.
 * [build.properties](build/build.properties) - Default build properties that can
 be overridden on a global or per module basis.

### Build Properties

Many aspects of the suite build such as file locations, executable names, etc... are 
parameterized into build properties. The default [build.properties](build/build.properties)
contains a list of default values. Often these default properties must be overridden to 
cater to the environment (eg. Windows vs Unix) or to cater to specifics of a particular module. 

There are two ways to override build properties. 

1. The first is to specify them directly to the and build command with the Java system property (-D) syntax. For example:

          % ant -Dsuite.build_cat=release build

1. Creating a file named `local.properties` either at the global level or at the module level. The global `local.properties` is located in the [build](build) directory next to 
`build.properties`. Module specific `local.properties` files are located next to the module `build.xml` file. Naturally the module specific overrides properties from the global file. 

Using any combination of the above method it should never be necessary to modify the `build.properties` file directly. 

## Modules

The suite repository is composed of the following modules.

* [dashboard](dashboard/README.md)
* [docs](docs/README.md)
* [geoexplorer](geoexplorer/README.md)
* [geoserver](geoserver/README.md)
* [geowebcache](geowebcache/README.md)
* [jetty](jetty/README.md)
* [recipes](recipes/README.md)
* [webapp-sdk](sdk/README.md)

Consult the module README files for module specific information. 

Build Environment
-----------------

If you don't build GeoTools, GeoServer, or GeoWebCache locally on a regular 
basis you can skip this section.

The suite builds its own internal versions of many components like GeoTools and 
GeoServer. To keep these builds separate it is recommended that you set up an 
virtual environment for the suite build. 

Tools like `virtualenv <http://pypi.python.org/pypi/virtualenv>`_ and `virtualenvwrapper <http://www.doughellmann.com/projects/virtualenvwrapper/>`_
are useful for creating virtual environments with configuration specific to a   particular project. It is recommended that you set up a  "virtualenv" 
specifically for the suite. In that virtualenv you can configure custom settings for maven, etc...


Repository Setup
----------------

The suite repository contains submodules that pull in external dependencies. 
After cloning the repository you must initialize the submodules::

  % git clone git://github.com/opengeo/suite.git suite
  % cd suite
  % git submodule init
  % git submodule sync
  % git submodule update

Building
--------

If you are building the suite locally for the first time you *must* do a full 
build::

  % mvn clean install -Dfull

The above command will build everything, including all external dependencies.
Dropping the ``-Dfull`` flag will only build the core suite components::

  % mvn clean install

To build a distribution a full build must first be completed. After which the 
following command is used::

  % mvn assembly:attached 

Resulting artifacts will be located in the ``target`` directory. 

The build and assembly commands can also be merged into one::

  % mvn clean install assembly:attached -Dfull
