import http from 'k6/http';
import {check} from 'k6';

export default function() {
    const url = 'http://localhost:8080/api/v1/hello'
    let res = http.get(url);

    check(res, {
        'status is 2xx': (r) => r.status >= 200 && r.status <= 229
    })
}