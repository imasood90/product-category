#!/bin/bash

load-all-images() {
  local name safename noextension tag
    directory=$PWD/dockerimages
    file_extension="tar.gz"

  for image in $(find "$directory" -name \*.$file_extension); do
    if [[ ! -z "$filter" ]] && [[ ! "$image" =~ "$filter" ]];then
      continue
    fi
    echo "Loading $image ..."
    docker load -i $image
  done
}

#load-all-images

docker-compose up
