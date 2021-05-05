load:
	k6 run\
		--vus 0\
		--stage 5s:20 \
		--stage 20s:20 \
		--stage 5s:0 \
		k6/user-post.js
