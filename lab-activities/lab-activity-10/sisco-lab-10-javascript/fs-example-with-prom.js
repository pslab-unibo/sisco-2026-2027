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
