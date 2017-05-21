// @flow
const seatNumberToName = (seat: number): string => {
  switch (seat) {
    case 1:
      return 'Bow';
    case 8:
      return 'Stroke';
    case 9:
      return 'Cox';
    default:
      return seat.toString(10);
  }
};

export default seatNumberToName;
