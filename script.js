import http from "k6/http";
import {check, sleep} from "k6";

export const options = {
    stages: [
        {duration: "30s", target: 5},
        {duration: "3m", target: 50},
        {duration: "30s", target: 0},
    ],
    thresholds: {
        http_req_failed: ["rate<0.01"],
        http_req_duration: ["p(95)<200"],
    },
};

export default function script() {
    const itemCode = Math.floor(Math.random() * 3) + 1;
    const quantity = Math.floor(Math.random() * 10) + 1;

    let url = `http://host.docker.internal:8081/api/orders/request`;

    const payload = JSON.stringify({
        "customer_name": "htoohtoo",
        "total_amount": 1000,
        "order_items": [
            {
                "item_code": `${itemCode}00L`,
                "quantity": quantity,
            },
        ],
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    check(res, {"status was 200": (response) => response.status === 200});
    sleep(1);
}
