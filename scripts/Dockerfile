ARG DEBIAN_FRONTEND=noninteractive
FROM riscv64/ubuntu:22.04
RUN apt-get -y update
RUN apt install -y apt-utils
RUN apt-get -y upgrade
RUN apt install -y gcc make binutils git vim
RUN apt install -y lsb-release htop

