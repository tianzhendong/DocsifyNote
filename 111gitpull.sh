#!/bin/bash
cd /usr/local/nginx/www/NoteBooks
git pull
/usr/local/nginx/sbin/nginx
/usr/local/nginx/sbin/nginx -s reload
