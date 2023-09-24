//run the file using "node index.js"
const axios = require('axios');

// Free API Endpoint available with for first 10 seconds.
const API_ENDPOINT = 'http://api.mathjs.org/v4/';

//maximum requests the Public API can handle
const MAX_CONCURRENT_REQUESTS = 50;

// List of mathematical expressions to evaluate
const expressions = [
  '2 + 2',
  '3 * 5',
  '10 / 2',
];

//function to call API
async function evaluateExpression(oneexp) {

  //headers required for the public API
  const headers = {
    'Content-Type': 'application/json',
  };
  //requestbody required for the public API
  const requestBody = {
    "expr": [oneexp],
    "precision": 14
  };

  
  axios.post(API_ENDPOINT, requestBody, { headers })
.then((response) => {
  // Handle the API response
  console.log(oneexp+'  =>', response.data.result);
})
.catch((error) => {
  // Handle any errors
  console.error('Error:', error);
});
}

async function main() {
  const tasks = [];

  for (const expression of expressions) {
    const task = evaluateExpression(expression);
    tasks.push(task);

    // Implement rate limiting by sleeping if necessary
    if (tasks.length >= MAX_CONCURRENT_REQUESTS) {
      await Promise.all(tasks);
      tasks.length = 0;
      await new Promise((resolve) => setTimeout(resolve, 1000)); // Adjust as needed
    }
  }

  // Wait for any remaining tasks to complete
  await Promise.all(tasks);
}

main();