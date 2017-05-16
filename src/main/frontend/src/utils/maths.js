// @flow
export const round = (value: number, decimals:number = 0):number => Number(Math.round(value.toString(10)+'e'+decimals.toString(10))+'e-'+decimals);
