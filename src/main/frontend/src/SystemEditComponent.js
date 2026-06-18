import { useParams } from 'react-router-dom';

const SystemEditComponent = () => {

    const { location } = useParams();

    return (
        <div>{ location }</div>
    );
}

export default SystemEditComponent;