#!/usr/bin/expect
#

# grab the password
stty -echo
send_user "\n"
send_user -- "Password for josh.king"
expect_user -re "(.*)\n"
send_user "\n"
stty echo
set pass $expect_out(1,string)

stty -echo
send_user -- "\n$pass\n"
stty echo

