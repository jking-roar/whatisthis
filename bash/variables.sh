#!/bin/bash

set -x

echo Variable substitution

VAR_X=varX

echo VAR_X is $VAR_X

VAR_Y=${VAR_X:-varY}
echo VAR_Y is $VAR_Y

VAR_Z=${NOT_DEFINED:-someDefaultValue}
echo VAR_Z is $VAR_Z
