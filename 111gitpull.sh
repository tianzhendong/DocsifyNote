#!/bin/bash
cd /usr/local/nginx/www/DocsifyNote
git pull origin master
# /usr/local/nginx/sbin/nginx
/usr/local/nginx/sbin/nginx -s reload