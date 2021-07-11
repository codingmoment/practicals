import * as actionTypes from './actions';

const initialState = {
  persons: []
}

const reducer = (state = initialState, action) => {

  if (action.type === actionTypes.ADD) {
    const newPerson = {
      id: Math.random(), // not really unique but good enough here!
      name: action.personData.name,
      age: action.personData.age
    }

    return { persons: state.persons.concat(newPerson) };

  } else if (action.type === actionTypes.DELETE) {
    return { persons: state.persons.filter(person => person.id !== action.id) }
  }

  return state;
};

export default reducer;