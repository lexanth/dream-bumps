const seatNumberToName = (seat) => {
  switch (seat) {
    case 1:
      return 'Bow';
    case 8:
      return 'Stroke';
    case 9:
      return 'Cox';
    default:
      return seat;
  }
};

export default seatNumberToName;
