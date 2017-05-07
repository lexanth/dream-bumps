import copyAndAddIfNotPresent from './copyAndAddIfNotPresent';

test('Adds an item and returns a new array if not present (integers)', () => {
  const inputArray = [1, 2, 3, 4, 5];
  Object.freeze(inputArray);

  const outputArray = copyAndAddIfNotPresent(inputArray, 6);

  expect(inputArray.length).toBe(5);
  expect(outputArray.length).toBe(6);
  expect(outputArray).toContain(6);
  expect(inputArray).not.toContain(6);
});

test('Returns same array unmodified if item present (integers)', () => {
  const inputArray = [1, 2, 3, 4, 5];
  Object.freeze(inputArray);

  const outputArray = copyAndAddIfNotPresent(inputArray, 3);

  expect(inputArray.length).toBe(5);
  expect(outputArray.length).toBe(5);
  expect(outputArray).toBe(inputArray);
  expect(outputArray.indexOf(3)).toBe(2);
});

test('Returns a new array with just the element if given a null array (integers)', () => {
  const outputArray = copyAndAddIfNotPresent(null, 7);
  expect(outputArray.length).toBe(1);
  expect(outputArray).toContain(7);
});

test('Returns a new array with just the element if given undefined (integers)', () => {
  const outputArray = copyAndAddIfNotPresent(undefined, 7);
  expect(outputArray.length).toBe(1);
  expect(outputArray).toContain(7);
});

test('Adds an item and returns a new array if not present (strings)', () => {
  const inputArray = ['A', 'B', 'C', 'D', 'E'];
  Object.freeze(inputArray);

  const outputArray = copyAndAddIfNotPresent(inputArray, 'F');

  expect(inputArray.length).toBe(5);
  expect(outputArray.length).toBe(6);
  expect(outputArray).toContain('F');
  expect(inputArray).not.toContain('F');
});

test('Returns same array unmodified if item present (strings)', () => {
  const inputArray = ['A', 'B', 'C', 'D', 'E'];
  Object.freeze(inputArray);

  const outputArray = copyAndAddIfNotPresent(inputArray, 'B');

  expect(inputArray.length).toBe(5);
  expect(outputArray.length).toBe(5);
  expect(outputArray).toBe(inputArray);
  expect(outputArray.indexOf('B')).toBe(1);
});

test('Returns a new array with just the element if given a null array (strings)', () => {
  const outputArray = copyAndAddIfNotPresent(null, 'G');
  expect(outputArray.length).toBe(1);
  expect(outputArray).toContain('G');
});

test('Returns a new array with just the element if given undefined (strings)', () => {
  const outputArray = copyAndAddIfNotPresent(undefined, 'H');
  expect(outputArray.length).toBe(1);
  expect(outputArray).toContain('H');
});
