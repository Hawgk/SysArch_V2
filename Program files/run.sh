#!/usr/bin/env bash

# This script is coded to work with Raspbian (Debian based distro)

# Check Java istallation and version
echo "Checking Java installation..."
if type -p java; then
    _java=java
else
    _install=true
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [[ "$version" < "11" ]]; then
        _install=true
    fi
fi

if [[ "$_install" ]]; then
    sudo apt update
    sudo apt -y install default-jdk
fi

echo "OpenJDK 11< installed. Proceeding..."

java -jar program.jar