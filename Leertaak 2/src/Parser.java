import java.util.Stack;

public class Parser {

	private String lastDate = "1970-00-00";
	private String lastTime = "00:00:00";

	private final Stack<Float> tempStack = new Stack30<>();
	private final Stack<Float> dewpStack = new Stack30<Float>();
	private final Stack<Float> stpStack = new Stack30<Float>();
	private final Stack<Float> slpStack = new Stack30<Float>();
	private final Stack<Float> visibStack = new Stack30<Float>();
	private final Stack<Float> wdspStack = new Stack30<Float>();
	private final Stack<Float> prcpStack = new Stack30<Float>();
	private final Stack<Float> sndpStack = new Stack30<Float>();
	private final Stack<Float> cldcStack = new Stack30<Float>();

	public boolean parse(FullMeasurement m, String line, int n) {
		if(m == null){return false;}
		line = line.replaceAll("[^0-9?!.\\-:]", "");
		float val = Float.parseFloat(line);
		/*
		if((n >= 3 && n <= 10) || n == 12){
			if(line.equals("")){
				val = fixVal(n);
			}
			else{
				val = Float.parseFloat(line);
			}
		}*/
		switch(n) {
			case 0:
				if(line.equals("")){
					m = null;
					return false;
				}
				m.setStn(Integer.parseInt(line));
				break;
			case 1:
				if(line.equals("")) {m.setDate(lastDate);}
				else {
					m.setDate(line);
					lastDate = line;
				}
				break;
			case 2:
				if(line.equals("")) {m.setTime(lastTime);}
				else {
					m.setTime(line);
					lastTime = line;
				}
				break;
			case 3:
				if(tempStack.size() == 30){
					float avg = 0;
					for(Float temp : tempStack) {avg += (temp / 30);}
					if(avg * (float)1.2 < val) {val = avg * (float)1.2;}
					else if (avg / (float)1.2 > val) {val = avg / (float)1.2;}
				}
				m.setTemp(val);
				tempStack.push(val);
				break;
			case 4:
				m.setDewp(val);break;
			case 5:
				m.setStp(val);break;
			case 6:
				m.setSlp(val);break;
			case 7:
				m.setVisib(val);break;
			case 8:
				m.setWdsp(val);break;
			case 9:
				m.setPrcp(val);break;
			case 10:
				m.setSndp(val);break;
			case 11:
				if(line.equals("")){m.setFrshtt("000000");}
				else{m.setFrshtt(line);}break;
			case 12:
				m.setCldp(val);break;
			case 13:
				if(line.equals("")){m.setWinddir(-1);}
				else{m.setWinddir(Integer.parseInt(line));}break;
		}
		return true;
	}

	public boolean parse(TempMeasurement m, String line, int n) {
		if (m == null) {return false;}
		line = line.replaceAll("[^0-9?!.\\-:]", "");
		switch(n) {
			case 0:
				if (line.equals("")) {
					m = null;
					return false;
				}
				m.setStn(Integer.parseInt(line));
				return true;
			case 1:
				if (line.equals("")) {
					m.setDate(lastDate);
				} else {
					m.setDate(line);
					lastDate = line;
				}
				return true;
			case 2:
				if (line.equals("")) {
					m.setTime(lastTime);
				} else {
					m.setTime(line);
					lastTime = line;
				}
				return true;
			case 3:
				m.setTemp(Float.parseFloat(line));
				return true;
			default:
				return true;
		}
	}

	/*
	private Float fixVal(int n){
		Stack<Float> s;
		switch (n){
			case 3:
				s = tempStack; break;
			case 4:
				s = dewpStack; break;
			case 5:
				s = stpStack; break;
			case 6:
				s = slpStack; break;
			case 7:
				s = visibStack; break;
			case 8:
				s = wdspStack; break;
			case 9:
				s = prcpStack; break;
			case 10:
				s = sndpStack; break;
			case 12:
				s = cldcStack; break;
			case 13:
				return (float)-1;
			default:
				return (float)0.0;
		}
		float avg = 0;
		if(s.size() > 0){
			for(float val : s){
				avg += val / s.size();
			}
		}
		return avg;
	}*/

	private class Stack30<T> extends Stack<T> {

		private Stack30() {
			super();
		}

		@Override
		public T push(T object) {
			//If the stack is too big, remove elements until it's the right size.
			int maxSize = 30;
			if (this.size() == maxSize) {
				this.remove(0);
			}
			return super.push(object);
		}
	}
}