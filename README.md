# Algorithm_2_Damascus_University_2024

✅ Professional README for the repository: concise overview, structure, build/run instructions, usage notes, and contribution guide.

---

## Project Overview 🔧

This repository contains Java projects used for coursework on tree structures and rectangle algorithms (Damascus University, 2024). It includes two main modules:

- **algorithm2** — utilities and visualizers for converting between generic trees and binary trees, exporting/importing trees, and GUI drawing tools.
- **OneAlogrithm** — algorithms for parsing expressions into binary trees, rectangle partitioning/rotation, counting rectangles, and GUI visualization.

Both modules are written in plain Java (no external build tools required) and include GUI components for drawing results.

---

## Key Features ✨

- Generic-to-binary tree conversion and export/import
- Binary tree drawing and visualization
- Expression parsing to binary trees
- Rectangle processing (rotate, export, count valid rectangles)
- Simple Swing-based GUI frames for visualization

---

## Repository Structure 📁

- `algorithm2/`

  - `src/` — Java source files for tree conversion, drawings, and `Main.java` entrypoint
  - Important files: `GenericTree.java`, `binaryTree.java`, `DrawG.java`, `DrawB.java`, `BinaryTreeDrawer.java`

- `OneAlogrithm/`
  - `src/` — Java source files for rectangle algorithms and `Main.java` entrypoint
  - Important files: `Rectangle.java`, `NumberOfRectangles.java`, `RectangleFrame.java`, `readRectangle.java`

---

## Prerequisites ✅

- Java Development Kit (JDK) 8 or later installed
- (Optional) An IDE such as IntelliJ IDEA or Eclipse for convenient running and debugging

---

## Build & Run (CLI) ▶️

> NOTE: Some `Main.java` files currently reference absolute input paths. See the "Paths & Inputs" section below for how to adapt them.

From the repository root, you can compile and run each module from its `src` directory.

Example: run `algorithm2` module

```bash
cd algorithm2/src
javac *.java
java Main
```

Example: run `OneAlogrithm` module

```bash
cd OneAlogrithm/src
javac *.java
java Main
```

If you prefer an IDE, import the project as a single project (or two modules) and run `Main.java` from the desired module.

---

## Paths & Inputs ⚠️

- Many files in `src` reference input files using absolute paths (e.g., `extended_tree.txt`, `Rect.txt`, `RotateRectangle.txt`). To run locally:
  - Option A: update file paths in `Main.java` to relative paths that point to `src/` files in this repo (recommended).
  - Option B: place the input files at the same absolute path used in the code.

Example relative path change in `Main.java`:

```java
File scr = new File("./extended_tree.txt");
```

Input files included (in `src`):

- `extended_tree.txt`, `binary_tree.txt` (algorithm2)
- `Rect.txt`, `RotateRectangle.txt` (OneAlogrithm)

---

## Testing & Usage 💡

- The programs print status and some outputs to the console and open GUI frames for visualization.
- To test algorithm behavior, modify or create input files (expression strings or rectangle data) and run `Main`.

---

## Contributing 🤝

- Feel free to open issues or submit pull requests to:
  - make paths configurable (e.g., via args or configuration file)
  - add unit tests
  - improve GUI and usability

Please add clear descriptions and, when applicable, sample inputs with expected results.

---

## License

This repository does not include a license file by default. If you want to add one, consider the MIT License for permissive reuse. Add a `LICENSE` file at the repository root.

---

## Contact

If you have questions or want help improving this README or project, open an issue in this repository or contact the owner/maintainer.

---

Thank you — enjoy exploring the algorithms and visualizations! ✅
