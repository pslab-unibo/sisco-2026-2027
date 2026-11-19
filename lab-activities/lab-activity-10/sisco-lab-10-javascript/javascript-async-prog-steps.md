## Asynchronous Programming - From CPS to async/await

### step 1 - Async programming with CPS

#### step 1.1 - toy async

```
console.log("1. before");
setTimeout(() => {
    console.log("3. inside");
}, 1000);
console.log("2. after");
```
#### step 1.2 - ...and if timeout is zero?

```
console.log("1. before");
setTimeout(() => {
    console.log("3. inside");
}, 0);
console.log("2. after");
```
#### step 1.2 - more realistic example:

```
const fs = require('fs');
console.log ("[LOG] going to read file..");
fs.readFile('/sisco-lab/fs-example.js', 'utf8', (err, data) => {
  if (err) throw err;
  console.log ("[LOG] file content:");
  console.log(data);
});
console.log ("[LOG] reading in progress..");
```

#### step 1.3 - callback hell

```
console.log("0");
setTimeout(() => {
    console.log("1");
    setTimeout(() => {
        console.log("2");
            setTimeout(() => {
                console.log("3");
            }, 1000);
    }, 1000);
}, 1000);
```

### step 2 - Introducing promises

#### step 2.1 - Promises..

```
const fsPromises = require('fs').promises;

console.log ("[LOG] going to read file..");
let p = fsPromises.readFile('/sisco-lab/fs-example-with-prom.js','utf-8');
p.then((data) => {
  console.log ("[LOG] file content:");
  console.log(data);
}).catch((err) => {
  console.error("[ERROR] failed to read file:", err);
});
console.log ("[LOG] reading in progress..");
```

#### step 2.2 - Chaining promises

```
const fsPromises = require('fs').promises;

console.log ("[LOG] going to read file..");
let p = fsPromises.readFile('/sisco-lab/fs-example-with-prom.js','utf-8');
p.then((data) => {
  console.log ("[LOG] file 1 content:");
  console.log(data);
  return fsPromises.readFile('/sisco-lab/hello-world.js','utf-8');
}).then((data) => {
  console.log ("[LOG] file 2 content:");
  console.log(data);
})
.catch((err) => {
  console.error("[ERROR] failed to read file:", err);
});
console.log ("[LOG] reading in progress..");
```

#### step 2.3 - Aggregating promises...

```
const fsPromises = require('fs').promises;

console.log ("[LOG] going to read file..");
let p1 = fsPromises.readFile('/sisco-lab/fs-example-with-prom.js','utf-8');
let p2 = fsPromises.readFile('/sisco-lab/hello-world.js','utf-8');
Promise.all([p1,p2])
.then((values) => {
  console.log ("[LOG] file 1 content:");
  console.log(values[0]);
  console.log(values[1]);
})
.catch((err) => {
  console.error("[ERROR] failed to read file:", err);
});
console.log ("[LOG] reading in progress..");
```

#### step 2.4 - Creating a promise

```
function delay(t) {
  let p = new Promise((resolve,reject) => {
	  setTimeout(() => {
      resolve();
    },  t)
	});
  return p;
}

console.log("waiting for 5 seconds..");
delay(5000)
.then(() => {
  console.log("done");
});
```

#### step 2.5 - Promise issues: eagerness...

```
function delay(t){
  let promise = new Promise(
    function (resolve,reject){
      setTimeout(() => { resolve(); },t);
  });
  return promise;
}

function getTime(){
  let time = new Date().getTime();
  console.log(time);
}

getTime();
delay(1000)
.then(getTime)
.then(delay(1000)) 
.then(getTime);
```

#### step 2.5 - Promise issues: loops...

```
function delay(t){
  let promise = new Promise(
    (resolve,reject) => {
      setTimeout(function(){
        resolve();
      },t);
  });
  return promise;
}

for (let i = 0; i < 3; i++){
  delay(1000).then(() => {
    let time = new Date().getTime();
    console.log(i + " at " + time);
  })
}
```

### step 3 - Introducing async/await

#### step 3.1 - Basic async/await...

````
function delay(t){
  let promise = new Promise(
    (resolve,reject) => {
      setTimeout(function(){
        resolve();
      },t);
  });
  return promise;
}

async function main(){
	console.log("before");
	await delay(5000);
	console.log("after");
}

main();
````
#### step 3.2 - await with result

```
const fsPromises = require('fs').promises;

async function main(){
  console.log ("[LOG] going to read file..");
  let data1 = await fsPromises.readFile('/sisco-lab/fs-example-with-prom.js','utf-8');
  console.log ("[LOG] file 1 content:");
  console.log(data1);
  let data2 = await fsPromises.readFile('/sisco-lab/hello-world.js','utf-8');
  console.log ("[LOG] file 2 content:");
  console.log(data2);
}

main()
```

#### step 3.3 - await with loops

```
function delay(t){
  let promise = new Promise(
    (resolve,reject) => {
      setTimeout(function(){
        resolve();
      },t);
  });
  return promise;
}

async function main(){
  for (let i = 0; i < 3; i++){
    await delay(1000);
    let time = new Date().getTime();
    console.log(i + " at " + time);
  }
}

main();
```

#### step 3.4 - await with aggregated promises

```
const fsPromises = require('fs').promises;

async function readFiles(){
  console.log ("[LOG] going to read file..");
  let p1 = fsPromises.readFile('/sisco-lab/fs-example-with-prom.js','utf-8');
  let p2 = fsPromises.readFile('/sisco-lab/hello-world.js','utf-8');
  return await Promise.all([p1,p2]);
}

async function main(){
    let values = await readFiles();
    console.log (values[0]);
    console.log (values[1]);
}

main() 
```

#### step 3.5 - await with lost updates

```
var x = 1;

function delay(t){
  let promise = new Promise(
    (resolve,reject) => {
      setTimeout(function(){
        resolve();
      },t);
  });
  return promise;
}

async function doInc(){
  let c = x;
  await delay(1000);	
  x = c + 1;
}

console.log("initial value: " + x);
let p1 = doInc();
let p2 = doInc();
await Promise.all([p1, p2]);
console.log("final value: " + x);
```









