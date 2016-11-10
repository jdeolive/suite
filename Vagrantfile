# -*- mode: ruby -*-
# vi: set ft=ruby :

# INSTRUCTIONS
# 1. Install virtualbox on your local machine
# 2. Install vagrant on your local machine
# 3. Make sure this repo is up-to-date and on the correct fork/branch.
# 4. `git submodule update --init --recursive`
# 3. `vagrant up`
# 4. When finished building (more than an hour), `vagrant ssh`.
#    The src directory is in /vagrant
#    The build output is in build_output.txt
# 5. To build again from the VM do `~/build`
#    To build again from the host do `vagrant ssh -c build`

maven_major = '3'
maven_version = '3.3.9'

Vagrant.configure(2) do |config|
  if Vagrant.has_plugin?("SyncedFolderRSyncPull")
    # `vagrant plugin install vagrant-rsync-pull`
    config.vm.synced_folder 'artifacts', '/var/www/suite/core', create: true, type: 'rsync_pull'
  end

  config.vm.provider :virtualbox do |vb, override|
    override.vm.box = 'ubuntu/trusty64'

    # Adjust these settings as needed.
    vb.memory = 4048
    vb.cpus = 2
  end

  config.vm.provider :aws do |aws, override|
    override.vm.box = 'dummy'
    override.vm.box_url = 'https://github.com/mitchellh/vagrant-aws/raw/master/dummy.box'
    aws.ami = "ami-d05e75b8"

    aws.instance_type = 'c3.2xlarge'
    aws.block_device_mapping = [
      {
        'DeviceName' => '/dev/sda1',
        'Ebs.VolumeSize' => 30,
      },
      {
        'DeviceName' => '/dev/sdb',
        'VirtualName' => 'ephemeral0',
      },
      {
        'DeviceName' => '/dev/sdc',
        'VirtualName' => 'ephemeral1',
      },
    ]
    aws.tags = {
      'Name' => "#{$AWS_KEYPAIR_NAME}-suite-build-vagrant"
    }

    override.ssh.username = 'ubuntu'
    override.ssh.forward_agent = true

    override.vm.synced_folder '.', '/vagrant', disabled: true
    #override.vm.synced_folder '.', '/mnt/vagrant', type: 'rsync' #, rsync__exclude: '.git/'
  end

  # Share host's maven cache. Comment the following line to disable.
  #config.vm.synced_folder '~/.m2', '/home/vagrant/.m2'

  config.vm.provision :shell, privileged: false, inline: <<SCRIPT
    set -ex

    DEBIAN_FRONTEND=noninteractive

    sudo apt-get -qq update

    if [ ! -d /vagrant ]; then
      echo 'src does not exist, loading with git'
      # Add github ssh host
      mkdir -p ~/.ssh
      cat <<EOF >> ~/.ssh/known_hosts
|1|MIwgkhDL5b0kJt26vY4zNR+p4Kc=|B8KFL+0hpvhkCV989BdzoCa6a8Q= ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAq2A7hRGmdnm9tUDbO9IDSwBK6TbQa+PXYPCPy6rbTrTtw7PHkccKrpp0yVhp5HdEIcKr6pLlVDBfOLX9QUsyCOV0wzfjIJNlGEYsdlLJizHhbn2mUjvSAHQqZETYP81eFzLQNnPHt4EVVUh7VfDESU84KezmD5QlWpXLmvU31/yMf+Se8xhHTvKSCZIFImWwoG6mbUoWf9nzpIoaSjB+weqqUUmpaaasXVal72J+UX2B+2RPW3RcT0eOzQgqlJL3RKrTJvdsjE3JEAvGq3lGHSZXy28G3skua2SmVi/w4yCE6gbODqnTWlg7+wC604ydGXA8VJiS5ap43JXiUFFAaQ==
|1|kdd70i6PaCKKUDqTshdaviel4kM=|7jmuzd0k+8iTeroj9WC412sZ4I8= ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAq2A7hRGmdnm9tUDbO9IDSwBK6TbQa+PXYPCPy6rbTrTtw7PHkccKrpp0yVhp5HdEIcKr6pLlVDBfOLX9QUsyCOV0wzfjIJNlGEYsdlLJizHhbn2mUjvSAHQqZETYP81eFzLQNnPHt4EVVUh7VfDESU84KezmD5QlWpXLmvU31/yMf+Se8xhHTvKSCZIFImWwoG6mbUoWf9nzpIoaSjB+weqqUUmpaaasXVal72J+UX2B+2RPW3RcT0eOzQgqlJL3RKrTJvdsjE3JEAvGq3lGHSZXy28G3skua2SmVi/w4yCE6gbODqnTWlg7+wC604ydGXA8VJiS5ap43JXiUFFAaQ==
EOF
      sudo apt-get install -y git
      sudo mkdir /vagrant
      sudo chown `whoami` /vagrant
      git clone https://github.com/boundlessgeo/suite.git /vagrant
      cd /vagrant
      git submodule update --init --recursive
      cd ~
    fi

    # Required for add-apt-repository
    sudo apt-get install -qqy software-properties-common

    JAVA_HOME=/usr/lib/jvm/java-8-oracle

    # Install Java
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo debconf-set-selections
    sudo add-apt-repository ppa:webupd8team/java
    sudo apt-get update -qq
    sudo apt-get install -qqy oracle-java8-installer unzip
    sudo rm -rf /var/cache/oracle-jdk8-installer

    # Install Java Cryptography Extension Unlimited Strength Jurisdiction Policy Files
    wget --header "Cookie: oraclelicense=accept-securebackup-cookie" \
      http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip
    sudo unzip -oj jce_policy-8.zip -d $JAVA_HOME/jre/lib/security/
    rm jce_policy-8.zip

    # Install build dependencies
    sudo apt-get -qqy install ant ivy git
    sudo mkdir /usr/local/maven
    curl http://archive.apache.org/dist/maven/maven-#{maven_major}/#{maven_version}/binaries/apache-maven-#{maven_version}-bin.tar.gz \
      | sudo tar -xzf- -C /usr/local/maven/ --strip 1
    sudo ln -s /usr/local/maven/bin/mvn /usr/bin/mvn
    sudo mkdir /etc/maven
    sudo tee /etc/maven/m2.conf <<EOF
main is org.apache.maven.cli.MavenCli from plexus.core

set maven.home default \\${user.home}/m2

[plexus.core]
optionally \\${maven.home}/lib/ext/*.jar
load       \\${maven.home}/lib/*.jar
EOF
    sudo ln -s  /usr/share/java/ivy.jar /usr/share/ant/lib/ivy.jar
    sudo apt-get -qqy install python-setuptools
    sudo easy_install jstools
    sudo apt-get -qqy install python-pip python-dev
    sudo pip install -U Sphinx==1.3.1

    # the standard ubuntu node install doesnt work very well and is VERY old.  Use this one instead
    curl -sL https://deb.nodesource.com/setup_4.x | sudo -E bash -
    sudo  apt-get install -y nodejs
    sudo npm install -g npm # Update npm

    sudo npm install -g bower
    sudo npm install -g grunt-cli
    sudo npm install -g gulp
    sudo npm install -g coffee-script
    sudo apt-get -y install gdal-bin ### Do we need one from our own repo?

    sudo mkdir -p /var/www/suite
    sudo chown `whoami` /var/www/suite

    # if [ ! -d /vagrant ]; then
    #   sudo ln -s /mnt/vagrant /vagrant
    # fi

    echo """#!/bin/bash
      cd /vagrant
      echo 'Starting build.'
      (ant -v build assemble publish 2>&1) | tee build_output.txt""" > ~/build
    chmod a+rx ~/build

    ~/build
SCRIPT
end
