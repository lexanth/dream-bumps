import {calculateBumpDividend, calculateRowOverDividendForPosition} from './dividends';

test('Dividend calculations', () => {
  it('Calculates correctly for mid division', () => {
    const bumpDividend = calculateBumpDividend(19, 92, 12, 1);
    const rowOverDividend = calculateRowOverDividendForPosition(19, 1);

    expect(bumpDividend).toEqual('');
    expect(rowOverDividend).toEqual('');
  });

  it('Calculates correctly for sandwich', () => {
    const bumpDividend = calculateBumpDividend(27, 92, 13, 1);
    const rowOverDividend = calculateRowOverDividendForPosition(27, 1);

    expect(bumpDividend).toEqual('');
    expect(rowOverDividend).toEqual('');
  });

  it('Calculates correctly for sandwich - 12 boat divs', () => {
    const bumpDividend = calculateBumpDividend(25, 92, 12, 1);
    const rowOverDividend = calculateRowOverDividendForPosition(25, 1);

    expect(bumpDividend).toEqual('');
    expect(rowOverDividend).toEqual('');
  });

  it('Calculates correctly for headship normal day', () => {
    // This should probably actually throw an error...
    // const bumpDividend = calculateBumpDividend(1, 92, 13);
    const rowOverDividend = calculateRowOverDividendForPosition(1, 1);

    // expect(bumpDividend).toEqual('');
    expect(rowOverDividend).toEqual('');
  });

  it('Calculates correctly for headship last day', () => {
    // const bumpDividend = calculateBumpDividend(1, 92, 13);
    const rowOverDividend = calculateRowOverDividendForPosition(1, 1);

    // expect(bumpDividend).toEqual('');
    expect(rowOverDividend).toEqual('');
  });

  it('Calculates correctly for chasing headship normal day', () => {
    const bumpDividend = calculateBumpDividend(2, 92, 13, 2);
    const rowOverDividend = calculateRowOverDividendForPosition(2, 1);

    expect(bumpDividend).toEqual('');
    expect(rowOverDividend).toEqual('');
  });

  it('Calculates correctly for chasing headship last day', () => {
    const bumpDividend = calculateBumpDividend(2, 92, 13, 3);
    const rowOverDividend = calculateRowOverDividendForPosition(2, 3);

    expect(bumpDividend).toEqual('');
    expect(rowOverDividend).toEqual('');
  });

  it('Calculates correctly for bottom extra crew', () => {
    const bumpDividend = calculateBumpDividend(92, 92, 13, 2);
    const rowOverDividend = calculateRowOverDividendForPosition(92, 1);

    expect(bumpDividend).toEqual('');
    expect(rowOverDividend).toEqual('');
  });
})
