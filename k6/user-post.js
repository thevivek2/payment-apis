import http from 'k6/http';
import {Counter, Rate, Trend} from 'k6/metrics';
import * as faker from 'https://cdnjs.cloudflare.com/ajax/libs/Faker/3.1.0/faker.min.js'

export let actual_http_req_duration = new Trend('actual_http_req_duration');
export let actual_http_reqs = new Counter('actual_http_reqs');
export let successful_requests = new Rate('successful_requests');


export default function () {
    let url = 'http://localhost:8080/api/v1/users';
    let header = getJsonHeader();
    let email  =  randomNumber() + faker.internet.email();
    let mobileNumber = randomNumber();
    let payload = {
        "email": email,
        "mobileNumber": mobileNumber,
        "name": faker.name.findName()
    }

    let result = http.post(url, JSON.stringify(payload), header);
    actual_http_req_duration.add(result.timings.duration);
    actual_http_reqs.add(1);
    /200/.test(result.status) ? successful_requests.add(true) : (successful_requests.add(false), logError(result));
}

export function randomNumber() {
    return 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    })
}

export function getJsonHeader() {
    return {headers: {"Content-Type": "application/json", "Accept": "application/json"}}
}

export function logError(res) {
    console.warn(res.status, res.body);
}


