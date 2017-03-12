export default (list, element) => {
  if (list === undefined || list === null) {
    return [element];
  }
  if (list.indexOf(element) === -1) {
    return [...list, element];
  }
  return list;
};
