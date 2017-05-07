import seatNumberToName from './seatNumberToName';

test('All seats', () => {
  it('gives the right name for bow', () => {
    const seatName = seatNumberToName(1);
    expect(seatName).toEqual('Bow');
  });

  it('gives the right name for 2', () => {
    const seatName = seatNumberToName(2);
    expect(seatName).toEqual('2');
  });

  it('gives the right name for 3', () => {
    const seatName = seatNumberToName(3);
    expect(seatName).toEqual('3');
  });

  it('gives the right name for 4', () => {
    const seatName = seatNumberToName(4);
    expect(seatName).toEqual('4');
  });

  it('gives the right name for 5', () => {
    const seatName = seatNumberToName(5);
    expect(seatName).toEqual('5');
  });

  it('gives the right name for 6', () => {
    const seatName = seatNumberToName(6);
    expect(seatName).toEqual('6');
  });

  it('gives the right name for 7', () => {
    const seatName = seatNumberToName(7);
    expect(seatName).toEqual('7');
  });

  it('gives the right name for stroke', () => {
    const seatName = seatNumberToName(8);
    expect(seatName).toEqual('Stroke');
  });

  it('gives the right name for cox', () => {
    const seatName = seatNumberToName(9);
    expect(seatName).toEqual('Cox');
  })

  // it('gives the right name for others', () => {
  //   const seatName = seatNumberToName(1);
  //   expect(seatName).toEqual('Bow');
  // })

})
