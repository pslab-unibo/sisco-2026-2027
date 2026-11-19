const fs = require('fs');

console.log ("[LOG] going to read file..");
fs.readFile('/sisco-lab/fs-example.js', 'utf8', (err, data) => {
  if (err) throw err;
  console.log ("[LOG] file content:");
  console.log(data);
});
console.log ("[LOG] reading in progress..");
