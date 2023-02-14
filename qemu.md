### ARM64的Centos7的安装流程
#### 1. 下载qemu的[win64安装包](https://qemu.weilnetz.de/w64/2022/qemu-w64-setup-20221110.exe)
#### 2. 下载qemu的
#### 3. 创建空镜像
```
qemu-img create -f qcow2 f:\centos-arm\centos.qcow2 40G
```
#### 3. 创建指定centos镜像的系统
```
qemu-system-aarch64.exe -m 8192 -cpu cortex-a72 -smp 8,sockets=4,cores=2 -M virt -bios f:\centos-arm\QEMU_EFI.fd -device VGA -device nec-usb-xhci -device usb-mouse -device usb-kbd -drive if=none,file=F:\centos-arm\centos.qcow2,id=hd0 -device virtio-blk-device,drive=hd0 -drive if=none,file=F:\centos-arm\CentOS-7-aarch64-Everything-2009.iso,id=cdrom,media=cdrom -device virtio-scsi-device -device scsi-cd,drive=cdrom -net nic -net tap,ifname=tap0,script=no,downscript=no
```