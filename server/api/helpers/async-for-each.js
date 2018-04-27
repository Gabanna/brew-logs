module.exports = {
  inputs: {
    array: {
      type: "ref",
      required: true
    },
    callback: {
      type: "ref",
      required: true
    }
  },
  fn: asyncForEach
};

async function asyncForEach(array, callback) {
  debugger
  for (let index = 0; index < array.length; index++) {
    await callback(array[index], index, array);
  }
}
