import {
  InputGroup,
  InputGroupAddon,
  InputGroupInput,
} from "@/components/ui/input-group";
import { SearchIcon } from "lucide-react";

interface Props {
  seachMovie: (movieName: string) => void;
}

const SearchBar = ({ seachMovie }: Props) => {
  return (
    <InputGroup className="h-10">
      <InputGroupInput
        placeholder="Search your favourite movies here!"
        onChange={(e) => seachMovie(e.target.value.toLowerCase())}
      />
      <InputGroupAddon>
        <SearchIcon />
      </InputGroupAddon>
    </InputGroup>
  );
};

export default SearchBar;
