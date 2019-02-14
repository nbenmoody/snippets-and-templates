#!/bin/bash
# Author: Ben Moody <ben.moody@e-gineering.com>

severity_level=error
GREEN='\033[0;32m'
OFF='\033[0m'

HELP() {
    echo -e "${GREEN}This script spins up a docker container with the ShellCheck utility on it and uses it to${OFF}"
    echo -e "evaluate all scripts in the targeted directory."
    echo -e "The targeted directory is assumed to be within the current working directory."
    echo -e "It exits on 1 if errors are found, but otherwise succeeds."
    echo -e "It removes the container once the script exits.\n"
    echo -e "Usage:  ./shellcheck_scripts /scripts_directory \n"
    echo -e "-h      --Displays this help menu."
    echo -e "-d      --Sets the target directory (containing scripts to check)."
    echo -e "-l      --Sets the level of error detection (error, warning, style, info) (default is error).\n"
}

while getopts d:l:h opt; do
    case $opt in
        d)
            scripts_directory=$OPTARG
            ;;
        l)
            severity_level=$OPTARG
            ;;
        h)
            HELP
            exit 1
            ;;
        \?)
            echo -e "Invalid command: Option not allowed\n"
            HELP
            exit 1
            ;;
    esac
done

case ${severity_level} in
    error | warning | info | style)
        echo "Severity-level set to ${severity_level}"
        ;;
    *)
        echo -e "Invalid command: Severity level ${severity_level} does not exist\n"
        exit 1
        ;;
esac

if [[ -z $scripts_directory ]]; then
    echo -e "Invalid command: No scripts directory targeted\n"
    exit 1
fi

if [[ ! $( ls ${PWD}/${scripts_directory} | grep .sh ) ]]; then
    echo -e "NO SCRIPTS DETECTED IN DIRECTORY: $scripts_directory"
    echo -e "Did you target the correct directory?"
    exit 1
fi


docker run --rm -v ${PWD}/${scripts_directory}:/mnt -e "severity_level=${severity_level}" docker-dtr-dev.ntrs.com/mw_private/shellcheck | grep $severity_level > shellcheck_results.txt
if [[ $( grep $severity_level shellcheck_results.txt ) ]]; then
    echo "Scripts checked in $PWD/scripts:"
    ls -l ${PWD}/${scripts_directory}
    echo "ERRORS DETECTED IN SCRIPTS"
    cat shellcheck_results.txt
    exit 1
else
    echo "NO ERRORS DETECTED FOR SCRIPTS IN $scripts_directory:"
    ls -l ${PWD}/${scripts_directory}
    exit 0
fi
