/* first async */

console.log("before");
setTimeout(() => {
    console.log("inside");
}, 1000);
console.log("after");
