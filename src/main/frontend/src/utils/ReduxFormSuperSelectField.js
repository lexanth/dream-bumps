import SuperSelectField from 'material-ui-superselectfield/lib/SuperSelectField';
import createComponent from 'redux-form-material-ui/lib/createComponent';
import mapError from 'redux-form-material-ui/lib/mapError';

// TODO - implement floatingLabelText if upstream doesn't do it first

export default createComponent(
  SuperSelectField,
  ({
    input: { onChange, value, onBlur, ...inputProps },
    onChange: onChangeFromField, ...props }) => ({
      ...mapError(props),
      ...inputProps,
      value,
      onChange: (val) => {
        onChange(val);
        if (onChangeFromField) {
          onChangeFromField(val);
        }
      },
      onBlur: () => onBlur(value)
    }
  ));
