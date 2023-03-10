while true
do
	echo "petting watchdog"
	echo 1 > /dev/watchdog
	sleep 5
done

