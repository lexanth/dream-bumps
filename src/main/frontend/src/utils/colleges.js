// @flow
const colleges = [
  {value: null, label: ''},
  {value: 'ball', label: 'Balliol'},
  {value: 'bras', label: 'Brasenose'},
  {value: 'chri', label: 'Christ Church'},
  {value: 'corp', label: 'Corpus Christi'},
  {value: 'exet', label: 'Exeter'},
  {value: 'grte', label: 'Green Templeton'},
  {value: 'hert', label: 'Hertford'},
  {value: 'jesu', label: 'Jesus'},
  {value: 'kebl', label: 'Keble'},
  {value: 'lady', label: 'Lady Margaret Hall'},
  {value: 'lina', label: 'Linacre'},
  {value: 'linc', label: 'Lincoln'},
  {value: 'magd', label: 'Magdalen'},
  {value: 'mans', label: 'Mansfield'},
  {value: 'mert', label: 'Merton'},
  {value: 'newc', label: 'New College'},
  {value: 'orie', label: 'Oriel'},
  {value: 'gree', label: 'Osler House'},
  {value: 'pemb', label: 'Pembroke'},
  {value: 'quee', label: 'Queen\'s'},
  {value: 'rege', label: 'Regent\'s Park'},
  {value: 'some', label: 'Somerville'},
  {value: 'sann', label: 'St Anne\'s'},
  {value: 'sant', label: 'St Antony\'s'},
  {value: 'sben', label: 'St Benet\'s'},
  {value: 'scat', label: 'St Catherine\'s'},
  {value: 'sedm', label: 'St Edmund Hall'},
  {value: 'shil', label: 'St Hilda\'s'},
  {value: 'shug', label: 'St Hugh\'s'},
  {value: 'sjoh', label: 'St John\'s'},
  {value: 'spet', label: 'St Peter\'s'},
  {value: 'trin', label: 'Trinity'},
  {value: 'univ', label: 'University'},
  {value: 'wadh', label: 'Wadham'},
  {value: 'wolf', label: 'Wolfson'},
  {value: 'worc', label: 'Worcester'}
];

export default colleges;

export const getCollegeName = (collegeCode: string) => {
  const college = colleges.find(college => college.value === collegeCode);
  if (college) {
    return college.label;
  }
  return '';
};
