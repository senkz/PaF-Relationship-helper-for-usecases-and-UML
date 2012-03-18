package fileexport;

public interface OutputFormat {
	void write(String fn, OutputFormat form);
}
