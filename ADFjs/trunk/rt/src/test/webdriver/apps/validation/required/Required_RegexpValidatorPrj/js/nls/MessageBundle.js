define({
  'root': 
   {
    'converter-globalizeDate': {
      'summary': 'You must enter a valid date.'
    },
    'converter-globalizeNumber': {
      'summary': 'You must enter a valid number.',
      'hint': 'Enter a whole number',
      'hint-format': 'Enter a number in the specified format {0}'
     
    },
    'validator-required': {
      'summary': 'A value is required'
    },
    'validator-regExp': {
      'summary': 'The value must match the provided pattern'
    },
    'validator-longRange' : {
      'summary': 'Value entered is not in the allowed range'
    },
    'validator-foolProofDate' : {
      'summary': 'You can\'t trick me! Enter a different date'
    },
    'app' : 
    {
      'validation-failed': 'Validation Failed!',
      'validator-conditionallyRequired': {'summary': 'A value is required for '},
      'validator-equalTo': {'summary': 'The passwords must match!'},
      'username': {
        'validator-required': {'summary': 'You must enter at least 3 alphanumeric characters'}
      },
      'password': {
        'validator-required': {'summary': 'You must enter a minimum of 6 characters including a' +
          'number, one uppercase and lowercase letter - E.g.: Hello2'}
      },
      'primaryPhone': {
        'validator-regExp': {'summary': 'You must enter 10 numbers.'}
      },
      'state': {
        'validator-required': {'summary': 'You must select a state!'}
      }
    }
    
  }
});