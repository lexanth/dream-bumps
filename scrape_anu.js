const axios = require('axios')
// const htmlparser = require('htmlparser')
const start = require('./start.json')
const cheerio = require('cheerio')

const crewNames = {
  BAL: 'Balliol',
  BRC: 'Brasenose',
  CHB: 'Christ Church',
  COO: 'Corpus Christi',
  EXC: 'Exeter',
  GTM: 'Green Templeton',
  HEC: 'Hertford',
  JEO: 'Jesus',
  KEB: 'Keble',
  LMH: 'Lady Margaret',
  LIN: 'Linacre',
  LIC: 'Lincoln',
  MAG: 'Magdalen',
  MAN: 'Mansfield',
  MER: 'Merton',
  NEC: 'New College',
  ORO: 'Oriel',
  OSG: 'Osler House',
  PMB: 'Pembroke',
  QCO: `Queen's`,
  RPC: `Regent's`,
  SOM: 'Somerville',
  SAC: `Anne's`,
  SAY: `Antony's`,
  SBH: `Benet's`,
  SCO: `Catherine's`,
  SEH: `Teddy`,
  SHI: `Hilda's`,
  SHG: `Hugh's`,
  SJO: `John's`,
  SPC: `Peter's`,
  TRO: 'Trinity',
  UCO: 'Univ',
  WAD: 'Wadham',
  WOO: 'Wolfson',
  WRO: 'Worcester'
}
let crewId = 1
let crews = []
Object.keys(start).map(collCode => {
  // console.log(crewNames[collCode])
  const collegeResults = start[collCode]
  collegeResults.men.map((pos, i) => {
    // console.log(`${crewId},${crewNames[collCode]} M${i + 1},${pos.start}`)
    crews.push({
      id: crewId,
      name: `${crewNames[collCode]} M${i + 1}`,
      start: pos.start,
      college: crewNames[collCode],
      sex: 'M',
      number: i + 1
    })
    crewId++
  })
  collegeResults.women.map((pos, i) => {
    // console.log(`${crewId},${crewNames[collCode]} W${i + 1},${pos.start}`)
    crews.push({
      id: crewId,
      name: `${crewNames[collCode]} W${i + 1}`,
      start: pos.start,
      college: crewNames[collCode],
      sex: 'W',
      number: i + 1
    })
    crewId++
  })
})

// console.log(crews)

const getCrewForName = name => {
  let attempt = crews.find(crew => crew.name.trim() === name)
  if (attempt) {
    return attempt
  }
  if (name.startsWith('St ')) {
    attempt = crews.find(crew => crew.name.trim() === name.substring(3).trim())
    if (attempt) {
      return attempt
    }
  }
  if (name.startsWith('St Edmund Hall')) {
    attempt = crews.find(
      crew => crew.name === `Teddy ${name.substring(15).trim()}`
    )
    if (attempt) {
      return attempt
    }
  }
  if (name.startsWith('Univ')) {
    const sex = name.split(' ')[1][0]
    const number = name.split(' ')[1][1]
    attempt = crews.find(crew => {
      return (
        crew.college.trim() === 'Univ' &&
        crew.sex === sex &&
        `${crew.number}` === number
      )
    })
    if (attempt) {
      return attempt
    }
  }
  if (name.startsWith('Lady Margaret Hall')) {
    const sex = name.split(' ')[3][0]
    const number = name.split(' ')[3][1]
    attempt = crews.find(crew => {
      return (
        crew.college.trim() === 'Lady Margaret' &&
        crew.sex === sex &&
        `${crew.number}` === number
      )
    })
    if (attempt) {
      return attempt
    }
  }
  if (name.startsWith("Regent's")) {
    const sex = name.split(' ')[2][0]
    const number = name.split(' ')[2][1]
    attempt = crews.find(crew => {
      return (
        crew.college.trim() === "Regent's" &&
        crew.sex === sex &&
        `${crew.number}` === number
      )
    })
    if (attempt) {
      return attempt
    }
  }
  // console.log(name)
}

const seatNumbers = {
  Bow: 1,
  '2': 2,
  '3': 3,
  '4': 4,
  '5': 5,
  '6': 6,
  '7': 7,
  Str: 8,
  Cox: 9
}

const data = require('./page').data
// console.log(data)
const $ = cheerio.load(data)
let crewMemberId = 1
// console.log(
$('.panel-primary')
  .find('.panel-info')
  .find('.panel-body')
  .find('.panel-default')
  .each((index, element) => {
    const crewName = $(element)
      .find('h4')
      .text()
      .trim()
    const crew = getCrewForName(crewName)
    if (crew) {
      crew.members = []
      $(element)
        .find('tr')
        .each((index, row) => {
          const posString = $(row)
            .find('th')
            .text()
          const name = $(row)
            .find('td')
            .text()
          const seat = seatNumbers[posString]
          if (seat) {
            crew.members.push({
              seat,
              name,
              id: crewMemberId++
            })
          }
        })
      // console.log(crew.name)
      // console.log(crew.members)
    }
  })

$('.panel-primary')
  .find('.panel-success')
  .find('.panel-body')
  .find('.panel-default')
  .each((index, element) => {
    const crewName = $(element)
      .find('h4')
      .text()
      .trim()
    // console.log(crewName)
    const crew = getCrewForName(crewName)
    if (crew) {
      // console.log('found')
      crew.members = []
      $(element)
        .find('tr')
        .each((index, row) => {
          const posString = $(row)
            .find('th')
            .text()
          const name = $(row)
            .find('td')
            .text()
          const seat = seatNumbers[posString]
          if (seat) {
            crew.members.push({
              seat,
              name,
              id: crewMemberId++
            })
          }
        })
      // console.log(crew.name)
      // console.log(crew.members)
    }
  })
const escape = str => (str.includes("'") ? `"${str}"` : `'${str}'`)

crews.forEach(crew => {
  const price = (300 - 66 * Math.log(crew.start)).toFixed(2)
  console.log(`INSERT INTO dreambumps.crew
  (id, name, sex, image, price)
  VALUES(${crew.id}, ${escape(crew.name)}, '${
    crew.sex === 'M' ? 'male' : 'female'
  }', 'NULL', ${price});
  `)

  console.log(`INSERT INTO dreambumps.crew_position_history
(id, \`day\`, \`position\`, bumps, dividend, crew_id)
VALUES(${crew.id}, 0, ${crew.start}, 0, 0, ${crew.id});
`)

  console.log(`INSERT INTO dreambumps.crew_price_history
(id, date_time, price, crew_id)
VALUES(${crew.id}, current_timestamp(), ${price}, ${crew.id});
`)
  if (!crew.members) {
    console.log('!!!!!!!!!!!!!!!!!!!!!')
    console.log(crew)
  }
  crew.members.forEach(member => {
    console.log(`INSERT INTO dreambumps.crew_member
    (id, seat, name, crew_id)
    VALUES(${member.id}, ${member.seat}, ${escape(member.name)}, ${crew.id});
    `)
  })
})
