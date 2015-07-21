#!/bin/bash

echo Variable substitution

VAR_X=varX

echo VAR_X is $VAR_X

VAR_Y=${VAR_X:-varY}
echo VAR_Y is $VAR_Y

VAR_Z=${NOT_DEFINED:-someDefaultValue}
echo VAR_Z is $VAR_Z


VAR_MAYBE=yes
VAR_DEPENDS=${NOT_DEFINED:-${VAR_MAYBE}}
echo VAR_DEPENDS is $VAR_DEPENDS

VAR_GETS_X=${VAR_X:-${VAR_MAYBE}}
echo VAR_GETS_X is $VAR_GETS_X

