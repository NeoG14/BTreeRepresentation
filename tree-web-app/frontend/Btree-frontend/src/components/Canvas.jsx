import {useEffect, useRef} from 'react'

const Canvas = props => {
    const ref=useRef();

    const draw = (context, count) => {
        context.clearRect(0,0,context.canvas.width,context.canvas.height)
        context.fillStyle = 'green';

        context.fillRect(20,10,100,50);
        context.clearRect(30,10,30,50);
        context.clearRect(70,10,30,50);
    }

    useEffect(() => {
        const canvas = ref.current;
        const context = canvas.getContext("2d");
        let count = 0;
        let animationId


        const rederer = () => {
            count ++
            draw(context,count)
            animationId=window.requestAnimationFrame(rederer)
        }
        rederer()

        return () => window.cancelAnimationFrame(animationId)

    },[])


    return <canvas ref={ref} {...props} />
}

export default Canvas